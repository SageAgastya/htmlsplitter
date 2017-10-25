package stage2;

import common.SplittingStrategy;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by phanindra on 25/10/17.
 */
public class Processor2 implements SplittingStrategy{


    public List<String> processing2(Document document) {
        Elements elements = doSplit(document);
        return elements.stream().map(element -> element.text()).collect(Collectors.toList());
    }

    public Elements doSplit(Document document) {
        Elements elements = Selector.select(".question,.answer", document.body());
        return elements;
    }
}
