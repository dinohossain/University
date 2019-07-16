package com.dating.app.idateu.Homepage.DBConnector;

import com.dating.app.idateu.UserDetail;

public class MatchingAlgorithm
    {
    static int match_status;

    private static void AssignMatchStatus()
        {
        if (UserDetail.getGender().equals("M")&&
                UserDetail.getOrientation().equals("S")) match_status = 1; //straight male
        else if (UserDetail.getGender().equals("F")&&
                UserDetail.getOrientation().equals("S")) match_status = 2; //straight female
        else if (UserDetail.getGender().equals("M")&&
                UserDetail.getOrientation().equals("G")) match_status = 3; //gay male
        else if (UserDetail.getGender().equals("F")&&
                UserDetail.getOrientation().equals("L")) match_status = 4; //lesbian female
        else if (UserDetail.getGender().equals("M")&&
                UserDetail.getOrientation().equals("B")) match_status = 5; //Bisexual male
        else if (UserDetail.getGender().equals("F")&&
                UserDetail.getOrientation().equals("B")) match_status = 6; //Bisexual female
        }

    public static boolean isSuitableMatch(String matchesGender, String matchesOrientation)
        {
        AssignMatchStatus();
        switch (match_status)
            {
            case (1):
                if (matchesGender.equals("F") && (matchesOrientation.equals("S")
                ||matchesOrientation.equals("B"))) return true;
                else return false;
            case (2):
                if (matchesGender.equals("M") && (matchesOrientation.equals("S")
                ||matchesOrientation.equals("B"))) return true;
                else return false;
            case (3):
                if (matchesGender.equals("M") && (matchesOrientation.equals("G")
                ||matchesOrientation.equals("B"))) return true;
                else return false;
            case(4):
                if (matchesGender.equals("F") && (matchesOrientation.equals("L")
                ||matchesOrientation.equals("B"))) return true;
                else return false;
            case(5):
                if ((matchesGender.equals("M") && (matchesOrientation.equals("G")
                ||matchesOrientation.equals("B")))
                ||(matchesGender.equals("F") && (matchesOrientation.equals("S")
                ||matchesOrientation.equals("B")))) return true;
                else return false;
            case (6):
                if (matchesGender.equals("M") && (matchesOrientation.equals("S")
                ||matchesOrientation.equals("B"))
                ||matchesGender.equals("F") && (matchesOrientation.equals("L")
                ||matchesOrientation.equals("B"))) return true;
                else return false;
            }
        return false;
        }
    }
