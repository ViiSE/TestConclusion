/*
 *   Copyright 2020 ViiSE
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package test.conclusion;

import org.testng.annotations.Test;
import test.conclusion.time.DateFormatter;
import test.conclusion.time.DateFormatterDefaultImpl;
import test.conclusion.time.DateParser;
import test.conclusion.time.DateParserDefaultPatternImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class TestClassTestNG {

    @Test
    public void calculateAvgTime() {
        String time1 = "00m 00s 096ms";
        String time2 = "00m 00s 081ms";
        String time3 = "00m 00s 165ms";

        DateParser parser = new DateParserDefaultPatternImpl();
        DateFormatter formatter = new DateFormatterDefaultImpl();

        String name1 = "readDepartments()";
        List<Date> testTimes1 = new ArrayList<>() {{
            add(parser.parse(time1));
            add(parser.parse(time2));
            add(parser.parse(time3));;
        }};
        TestMethod testMethod1 = new TestMethodDefaultImpl(name1, testTimes1);

        String time4 = "00m 00s 063ms";
        String time5 = "00m 00s 051ms";
        String time6 = "00m 00s 074ms";

        String name2 = "readUnits()";
        List<Date> testTimes2 = new ArrayList<>() {{
            add(parser.parse(time4));
            add(parser.parse(time5));
            add(parser.parse(time6));;
        }};
        TestMethod testMethod2 = new TestMethodDefaultImpl(name2, testTimes2);

        TestClass testClass = new TestClassDefaultImpl(
                "Sample name",
                new ArrayList<>() {{ add(testMethod1); }});
        testClass.addTestMethod(testMethod2);
        Map<String, Date> avgTimes = testClass.calculateAvgTime();

        avgTimes.forEach((methodName, date) ->
            System.out.println(methodName + ": " + formatter.format(date)));

        assertEquals(formatter.format(avgTimes.get("readDepartments()")), "00m 00s 114ms", "Average time is not average!");
        assertEquals(formatter.format(avgTimes.get("readUnits()")), "00m 00s 063ms", "Average time is not average!");
    }
}
