package com.anjlab.android.iab.v3.util;

import java.util.Scanner;

public class ResourcesUtil
{
    public static String loadFile(String path)
    {
        StringBuilder result = new StringBuilder();
        Scanner sc = null;
        try
        {
            sc = new Scanner(ResourcesUtil.class.getClassLoader().getResourceAsStream(path));
            while (sc.hasNextLine())
            {
                result.append(sc.nextLine());
            }
            sc.close();
        }
        finally
        {
            if (sc != null)
            {
                sc.close();
            }
        }
        return result.toString();
    }
}
