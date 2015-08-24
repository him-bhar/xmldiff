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

import java.util.HashSet;
import java.util.Set;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.NodeDetail;
import org.w3c.dom.Node;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;

public class IgnoringDifferenceListener implements DifferenceListener {
  
  private Set<Integer> ignoredDifferences = new HashSet<>();
  private Set<String> ignoredXPaths = new HashSet<>();
  
  {
    ignoredDifferences.add(DifferenceConstants.CHILD_NODELIST_LENGTH_ID);
    ignoredDifferences.add(DifferenceConstants.CHILD_NODELIST_SEQUENCE_ID);
    //ignoredXPaths.add("");
  }

  @Override
  public int differenceFound(Difference arg0) {
    System.out.println(arg0);
    NodeDetail controlNode = arg0.getControlNodeDetail();
    NodeDetail testNode = arg0.getTestNodeDetail();
    
    if (ignoredDifferences.contains(arg0.getId()) /*|| 
        ((controlNode != null && controlNode.getNode() != null && controlNode.getNode().getNodeType() == Node.TEXT_NODE) 
            || (testNode != null && testNode.getNode() != null && testNode.getNode().getNodeType() == Node.TEXT_NODE)) */) {
      System.out.println("IGNORING");
      System.out.println(arg0.toString());
      return RETURN_IGNORE_DIFFERENCE_NODES_IDENTICAL;
    } else {
      return RETURN_ACCEPT_DIFFERENCE;
    }
  }

  @Override
  public void skippedComparison(Node arg0, Node arg1) {
    // TODO Auto-generated method stub

  }

}
