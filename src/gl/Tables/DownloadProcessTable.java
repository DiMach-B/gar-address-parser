package gl.Tables;

public class DownloadProcessTable {

    private String FileName;
    private String Region;
    private Integer Inserting;
    private Integer Updating;
    private Integer Errors;

    public DownloadProcessTable(String FileName, String Region, Integer Inserting,
                                Integer Updating, Integer Errors) {

        this.FileName = FileName;
        this.Region = Region;
        this.Inserting = Inserting;
        this.Updating = Updating;
        this.Errors = Errors;
    }

    public String getFileName() {
        return FileName;
    }

    public String getRegion() {
        return Region;
    }

    public Integer getInserting() {
        return Inserting;
    }

    public Integer getUpdating() {
        return Updating;
    }

    public Integer getErrors() {
        return Errors;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public void setInserting(Integer inserting) {
        Inserting = inserting;
    }

    public void setUpdating(Integer updating) {
        Updating = updating;
    }

    public void setErrors(Integer errors) {
        Errors = errors;
    }
}
