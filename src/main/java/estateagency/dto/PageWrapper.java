package estateagency.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;

public class PageWrapper<T> implements Page<T> {

    public int MAX_PAGE_ITEM_DISPLAY = 5;

    private Page<T> page;
    private int start;
    private int finish;

    public PageWrapper(Page<T> page, int MAX_PAGE_ITEM_DISPLAY) {
        this.MAX_PAGE_ITEM_DISPLAY = MAX_PAGE_ITEM_DISPLAY;
        this.page = page;
        if (page.getTotalPages() <= MAX_PAGE_ITEM_DISPLAY) {
            start = 0;
            finish =  page.getTotalPages() - 1;
        } else {
            if (page.getNumber() + 1 <= MAX_PAGE_ITEM_DISPLAY - MAX_PAGE_ITEM_DISPLAY / 2) {
                start = 0;
                finish = MAX_PAGE_ITEM_DISPLAY - 1;
            } else if (page.getNumber() >= page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY / 2) {
                start = page.getTotalPages() - MAX_PAGE_ITEM_DISPLAY;
                finish = start + MAX_PAGE_ITEM_DISPLAY - 1;
            } else {
                start = page.getNumber() - MAX_PAGE_ITEM_DISPLAY / 2;
                finish = start + MAX_PAGE_ITEM_DISPLAY - 1;
            }
        }

    }

    public PageWrapper(Page<T> page) {
        this(page, 5);
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    @Override
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    public long getTotalElements() {
        return page.getTotalElements();
    }

    @Override
    public int getNumber() {
        return page.getNumber();
    }

    @Override
    public int getSize() {
        return page.getSize();
    }

    @Override
    public int getNumberOfElements() {
        return page.getNumberOfElements();
    }

    @Override
    public List<T> getContent() {
        return page.getContent();
    }

    @Override
    public boolean hasContent() {
        return page.hasContent();
    }

    @Override
    public Sort getSort() {
        return page.getSort();
    }

    @Override
    public boolean isFirst() {
        return page.isFirst();
    }

    @Override
    public boolean isLast() {
        return page.isLast();
    }

    @Override
    public boolean hasNext() {
        return page.hasNext();
    }

    @Override
    public boolean hasPrevious() {
        return page.hasPrevious();
    }

    @Override
    public Pageable nextPageable() {
        return page.nextPageable();
    }

    @Override
    public Pageable previousPageable() {
        return page.previousPageable();
    }

    @Override
    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return page.map(converter);
    }

    @Override
    public Iterator<T> iterator() {
        return page.iterator();
    }
}