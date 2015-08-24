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

public class FieldDifference {
  
  private final String key;
  private final String oldVal;
  private final String newVal;
  private final DifferenceType diffType;
  
  public FieldDifference(String key, String oldVal, String newVal, DifferenceType diffType) {
    this.key = key;
    this.oldVal = oldVal;
    this.newVal = newVal;
    this.diffType = diffType;
  }

  @Override
  public String toString() {
    return "FieldDifference [key=" + key + ", oldVal=" + oldVal + ", newVal=" + newVal + ", diffType=" + diffType + "]";
  }
  
  

}
