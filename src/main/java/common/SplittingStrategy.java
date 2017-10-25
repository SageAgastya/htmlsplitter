package common;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by phanindra on 25/10/17.
 */
public interface SplittingStrategy {
    /**
     *
     * @param document input html document to process on
     * @return html elements produced by desired selection.
     */
    Elements doSplit(Document document);
}
