package com.lwz.login_demo.entity.shuizhun;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 水准网平差处理
 * Created by zhouyuhang on 2019/4/11.
 */
@Data
public class AdjustLevel {
    private Matrix B;//误差方程系数矩阵
    private Matrix P;//观测值权阵
    private Matrix l;//误差方成常数项
    private Matrix Q;//协因数阵
    private Matrix x;//待定点改正数
    private Matrix V;//观测值改正数
    private Matrix Temp_M;//临时的属性
    private double derta;//单位权中误差
    private int n;//观测数
    private int t;//必要观测数

    public void LevelAdjust(List<LineClass> CurrentSegments, List<LPointClass> CurrentPoints, List<LPointClass> ControlPoints) throws Exception
    {

        n = CurrentSegments.size();//计算观测数
        t = CurrentPoints.size() - ControlPoints.size();//计算必要观测数，ControlPoints是水准网中的控制点，应去除不相关控制点。
        double temp = 0;
        B = new Matrix(n, t);//初始化
        P = new Matrix(n, n);
        l = new Matrix(n, 1);

        //再当前点中提取待定点，去除控制点
        List<LPointClass> TPoints = new ArrayList<LPointClass>();
        for (LPointClass Tp : CurrentPoints)
        {
            if (Tp.isIsControlP() == false)
                TPoints.add(Tp);
        }
        //k相当于观测边号
        for (int k = 0; k < n; k++)
        {
            //i，j为待定点参数的序号
            int i = Belong(TPoints, CurrentSegments.get(k).getSP().getPID());
            int j = Belong(TPoints, CurrentSegments.get(k).getEP().getPID());
            temp = 1.0 / CurrentSegments.get(k).getDistance();//权值
            P.SetNum(k, k, temp);
            //终点观测高程-终点近似高程
            temp = CurrentSegments.get(k).getSP().getH() + CurrentSegments.get(k).getDH() - CurrentSegments.get(k).getEP().getH();
            //改正数以mm为单位
            temp = temp * 1000;
            l.SetNum(k, 0, temp);//常数项设值
            if (CurrentSegments.get(k).getSP().isIsControlP() == false)
                B.SetNum(k, i, -1);//观测边起点系数为-1
            if (CurrentSegments.get(k).getEP().isIsControlP() == false)
                B.SetNum(k, j, 1);//观测边终点系数为+1
        }

        Q = Matrix.Inverse(Matrix.Mutiply(Matrix.Mutiply(B.Transpose(), P), B));//Q=BTPB_1
        x = Matrix.Mutiply(Q, Matrix.Mutiply(Matrix.Mutiply(B.Transpose(), P), l));//x=BTPB_1*BTPL
        V = Matrix.subtraction(Matrix.Mutiply(B, x), l);//V=BX-L
        //计算待定点平差值
        int kk = 0;
        for (LPointClass TP : CurrentPoints)
        {

            if (TP.isIsControlP() == false)
            {
                TP.setH(TP.getH() + x.getNum(kk, 0) / 1000);
                kk++;
            }
        }
        //计算精度
        Temp_M = Matrix.Mutiply(Matrix.Mutiply(V.Transpose(), P), V);
        derta = Math.sqrt(Temp_M.getNum(0, 0) / (n - t));
    }

    private static int Belong(List<LPointClass> CurrentPoints, String s)
    {
        for (LPointClass TPoint:CurrentPoints) {
            if (TPoint.getPID() == s)
                return CurrentPoints.indexOf(TPoint);
        }
        return -1;
    }
}
