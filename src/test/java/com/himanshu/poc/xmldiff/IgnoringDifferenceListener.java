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

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.DifferenceListener;
import org.w3c.dom.Node;

public class IgnoringDifferenceListener implements DifferenceListener {

  @Override
  public int differenceFound(Difference arg0) {
    System.out.println(arg0);
    if (arg0.getId() == DifferenceConstants.CHILD_NODELIST_LENGTH.getId()) {
      System.out.println("IGNORING \n\n");
      System.out.println(arg0.toString());
      System.out.println("\n\n");
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
