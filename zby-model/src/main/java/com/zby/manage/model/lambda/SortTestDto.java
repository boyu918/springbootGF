package com.zby.manage.model.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class SortTestDto {

    private String name;

    private String dtlDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDtlDate() {
        return dtlDate;
    }

    public void setDtlDate(String dtlDate) {
        this.dtlDate = dtlDate;
    }

    public SortTestDto() {
    }

    public SortTestDto(String name, String dtlDate) {
        this.name = name;
        this.dtlDate = dtlDate;
    }

    @Override
    public String toString() {
        return "SortTestDto{" +
                "name='" + name + '\'' +
                ", dtlDate='" + dtlDate + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("带的ff".toUpperCase());
        List<SortTestDto> list = new ArrayList<>();
        list.add(new SortTestDto("第liu","5555"));
        list.add(new SortTestDto("第五","5555"));
        list.add(new SortTestDto("第四","55555555"));
        list.add(new SortTestDto("第四","4444"));
        list.add(new SortTestDto("yyy","2233"));
        list.add(new SortTestDto("第三","2200"));
        list.add(new SortTestDto("第二","4456"));
        list.add(new SortTestDto("第一","2200"));
        Map<String,String> map = new HashMap<>();
        map.put("第一","1"); map.put("第二","2"); map.put("YYY","3"); map.put("第四","4"); map.put("第五","5");
        System.out.println(list.toString());
        list.sort(Comparator.comparing((SortTestDto sort)->map.get(sort.getName().toUpperCase())==null?"6":map.get(sort.getName().toUpperCase())).reversed().thenComparing(SortTestDto::getDtlDate));
        System.out.println(list.toString());
    }
}
