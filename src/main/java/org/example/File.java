package org.example;

import java.util.List;

public class File {

    private List<List<String>> content;
    private int pages;

    public List<List<String>> getContent() {
        return content;
    }

    public void setContent(List<List<String>> content) {
        this.content = content;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public File(List<List<String>> content, int length) {
        this.content = content;
        this.pages = (content.size() % length == 0) ? content.size() / length : content.size() / length + 1;
    }
}
