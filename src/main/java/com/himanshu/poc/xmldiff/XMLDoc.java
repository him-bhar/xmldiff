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

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLDoc {
  private final String filePath;
  private Document dom;
  private final String xmlString;
  
  public XMLDoc(String filePath, Document dom, String xmlString) {
    this.filePath = filePath;
    this.dom = dom;
    this.xmlString = xmlString;
  }
  
  public void buildDocument() throws ParserConfigurationException, SAXException, IOException {
    if (dom == null && xmlString == null) {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      dom = db.parse(filePath);
      dom.normalize();
    } else if (dom == null && xmlString != null) {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      dom = db.parse(new InputSource(new StringReader(xmlString)));
      dom.normalize();
    }
  }
  
  public Document getDom() {
    return dom;
  }
}
