/*
 * Copyright 2013 Himanshu Bhardwaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.himanshu.poc.xmldiff;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class CompareXMLsTest {
  
  @Test
  public void testCompare() throws XPathExpressionException {
    String xml1FilePath = CompareXMLsTest.class.getResource("/").getFile().concat("test_plain_1.xml");
    String xml2FilePath = CompareXMLsTest.class.getResource("/").getFile().concat("test_plain_2.xml");
    
    XMLDoc doc1 = new XMLDoc(xml1FilePath, null, null);
    XMLDoc doc2 = new XMLDoc(xml2FilePath, null, null);
    try {
      doc1.buildDocument();
      doc2.buildDocument();
    } catch (ParserConfigurationException | SAXException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    CompareXMLs comparator = new CompareXMLs(doc1, doc2);
    comparator.compare();
  }

}
