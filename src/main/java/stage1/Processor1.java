package stage1;

import common.SplittingStrategy;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by phanindra on 25/10/17.
 */
public class Processor1 implements SplittingStrategy {

    public List<String> processing1(Document document) {
        //split logic
        Elements elements = doSplit(document);

        // do something using the split output
        return elements.stream().map(element -> element.text()).collect(Collectors.toList());
    }

    public Elements doSplit(Document document) {
        Elements elements = Selector.select(".question,.answer", document.body());
        return elements;
    }
}
