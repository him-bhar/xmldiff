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

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.ElementNameAndTextQualifier;
import org.custommonkey.xmlunit.NodeDetail;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class CompareXMLs {
  private final XMLDoc doc1;
  private final XMLDoc doc2;
  
  private List<FieldDifference> fieldDifferences = new ArrayList<>();

  public CompareXMLs(XMLDoc doc1, XMLDoc doc2) {
    this.doc1 = doc1;
    this.doc2 = doc2;
    XMLUnit.setNormalize(true);
    XMLUnit.setNormalizeWhitespace(true);
    //XMLUnit.setCompareUnmatched(false);
    //XMLUnit.setIgnoreWhitespace(true);
  }

  public void compare() throws XPathExpressionException {
    Diff diff = new Diff(doc1.getDom(), doc2.getDom());
    diff.overrideDifferenceListener(new IgnoringDifferenceListener());
    diff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
    DetailedDiff detailedDiff = new DetailedDiff(diff);
    for (Object o : detailedDiff.getAllDifferences()) {
      Difference difference = ((Difference)o);
      System.out.println(difference+" with diff code "+difference.getId());
      //Reporting mismatch
      if (difference.getId() == DifferenceConstants.TEXT_VALUE_ID) {
        FieldDifference fieldDifference = new FieldDifference(generateLogicalXPath(difference.getControlNodeDetail().getNode()), difference.getControlNodeDetail().getValue(), difference.getTestNodeDetail().getValue(), DifferenceType.MISMATCHED);
        fieldDifferences.add(fieldDifference);
      }
    }
    System.out.println(fieldDifferences);
    /*System.out.println(detailedDiff.identical());
    System.out.println(detailedDiff.similar());*/
    //XMLDoc docInner = parseSpecialFields("/person/xmls/testxml", doc1.getDom());
    //System.out.println(docInner);
  }

  private String generateLogicalXPath(Node node) {
    if (node != null) {
      return generateLogicalXPath(node.getParentNode()).concat("/").concat(node.getNodeName());
    } else {
      return "/";
    }
  }

  private XMLDoc parseSpecialFields(String xPathExpression, Document dom) throws XPathExpressionException {
    XPathFactory xPathFactory = XPathFactory.newInstance();
    XPath xPath = xPathFactory.newXPath();
    Node doc1Node = (Node)xPath.evaluate(xPathExpression, dom, XPathConstants.NODE);
    String xml = doc1Node.getTextContent();
    XMLDoc xmlDoc = new XMLDoc(xPathExpression, dom, xml);
    return xmlDoc;
  }
}
