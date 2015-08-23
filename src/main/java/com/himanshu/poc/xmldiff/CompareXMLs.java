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

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;

public class CompareXMLs {
  private final XMLDoc doc1;
  private final XMLDoc doc2;

  public CompareXMLs(XMLDoc doc1, XMLDoc doc2) {
    this.doc1 = doc1;
    this.doc2 = doc2;
    XMLUnit.setNormalize(true);
    XMLUnit.setNormalizeWhitespace(true);
    XMLUnit.setCompareUnmatched(false);
  }

  public void compare() {
    Diff diff = new Diff(doc1.getDom(), doc2.getDom());
    diff.overrideDifferenceListener(new IgnoringDifferenceListener());
    diff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
    DetailedDiff detailedDiff = new DetailedDiff(diff);
    for (Object o : detailedDiff.getAllDifferences()) {
      System.out.println(((Difference)o)+" with diff code "+((Difference)o).getId());
    }
    System.out.println(detailedDiff.identical());
    System.out.println(detailedDiff.similar());
  }
}
