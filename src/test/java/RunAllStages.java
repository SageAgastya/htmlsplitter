import org.apache.commons.io.IOUtils;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import stage1.Processor1;
import stage2.Processor2;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by phanindra on 25/10/17.
 *
 * This class does the following
 * 1) Reads faq's page from ATO website
 * 2) Splits the question ans answers using css selector ".question,.answer"
 *    Which would output 30 elements. (15 questions + 15 answers)
 * 3) Runs processor1 code and processor2 code and verifies both of them produce the same output.
 */

public class RunAllStages {

    @Test
    public void test_output_of_stage1() {
        String fileAsString = getFileWithUtil("Faqs.html");
        Document document = Jsoup.parse(fileAsString);
        List<String> output1 = new Processor1().processing1(document);
        assertEquals("There should be 15 faqs and their answers", 30, output1.size());
        // faq1
        assertEquals("When is the cut-off date to submit my application?", output1.get(0));
        // answer1
        assertEquals("12 April 2017", output1.get(1));

        // faq2
        assertEquals("Who can I contact if I have an enquiry about these jobs or the application process?", output1.get(2));
        // answer2
        assertEquals("Email us at: EntryLevelPrograms@ato.gov.au", output1.get(3));

        // faq3
        assertEquals("Can I lodge a late application?", output1.get(4));
        // answer3
        assertEquals("Late applications are not accepted.", output1.get(5));

    }



    @Test
    public void test_stage1_and_stage2_produce_same() {
        String fileAsString = getFileWithUtil("Faqs.html");
        Document document = Jsoup.parse(fileAsString);

        List<String> output1 = new Processor1().processing1(document);

        List<String> output2 = new Processor2().processing2(document);


        Assert.assertThat("Both stages produced different results. Something wrong", output1,
                IsIterableContainingInOrder.contains(output2.toArray()));

    }
    private String getFileWithUtil(String fileName) {

        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
