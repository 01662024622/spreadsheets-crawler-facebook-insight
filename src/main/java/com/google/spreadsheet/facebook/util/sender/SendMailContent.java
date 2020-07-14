package com.google.spreadsheet.facebook.util.sender;
import java.time.LocalDate;
public class SendMailContent {
    public static String checkSheet(Boolean status,String type,String pageName,Boolean createNew){
        if (status) return getSuccess(type,pageName,createNew);
        else return getError(type,pageName);

    }
    private static String getSuccess(String type, String pageName, Boolean createNew){
        String date = LocalDate.now().toString();
        String codeContext ="";

        if (createNew){
            codeContext= "Hệ thống đã đẩy dữ liệu mới của '"+ type +"' có sheetName '"+pageName+"' lên database";
        }else {
            codeContext= "Hệ thống đã cập nhật lại của '"+ type +"' có sheetName '"+pageName+"' lên database do có sự thay đổi từ page sheet";
        }
        return "Hôm nay ngày: "+date+". \n"+codeContext;
    }

    private static String getError(String type, String pageName){
        String date = LocalDate.now().toString();
        String codeContext ="";

            codeContext= "Hệ thống đã phát hiện '"+ type +"' có sheetName '"+pageName+"' không phù hợp với format của các sheet";

        return "Hôm nay ngày: "+date+". \n"+codeContext;
    }

    public static String getErrorField(String type, String pageName,String sheetName,String fieldName){
        String date = LocalDate.now().toString();
        String codeContext ="";

        codeContext= "Hệ thống đã phát hiện '"+ type +"'  sheetName '"+pageName+"' tại tab '"+sheetName+"' có field '"+ fieldName +"' không phù hợp với cấu trúc đã có \n"
        +"Nếu muốn cập nhật cấu trúc bạn hãy liên hệ với bộ phận Data IT để thay đổi.";


        return "Hôm nay ngày: "+date+". \n"+codeContext;
    }

}
