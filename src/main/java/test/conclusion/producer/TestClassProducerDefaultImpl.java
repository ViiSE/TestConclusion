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

package test.conclusion.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import test.conclusion.TestClass;
import test.conclusion.TestMethod;

import java.util.List;

@Service("testClassProducerDefault")
public class TestClassProducerDefaultImpl implements TestClassProducer {

    private final ApplicationContext ctx;

    public TestClassProducerDefaultImpl(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public TestClass getTestClassDefaultInstance(String testClassName, List<TestMethod> testMethods) {
        return (TestClass) ctx.getBean("testClassDefault", testClassName, testMethods);
    }
}
