package gl.Tables;

import java.sql.Date;

public class DownloadHistoryGarTable {

    private String Uploaddate;
    private String Status;

    public DownloadHistoryGarTable(String Uploaddate, String Status) {

        this.Uploaddate = Uploaddate;
        this.Status = Status;
    }

    public String getUploaddate() {
        return Uploaddate;
    }

    public void setUploaddate(String uploaddate) {
        Uploaddate = uploaddate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
