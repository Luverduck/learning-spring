package springmvc.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

public class ModelView {

    // 모델
    private Map<String, Object> model = new HashMap<>();
    // 뷰의 이름
    private String viewName;

    // 생성자
    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
