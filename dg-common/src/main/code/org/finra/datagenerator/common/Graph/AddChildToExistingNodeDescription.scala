/*
 * Copyright 2014 DataGenerator Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.finra.datagenerator.common.Graph

import org.finra.datagenerator.common.NodeData.DisplayableData

import scala.beans.BeanProperty

/**
 * Describes the creation of a link between one node and another existing node as its child
 * @param parentNodeIndex Index of parent node
 * @param childNodeIndex Index of child node
 * @tparam T Node data type
 */
class AddChildToExistingNodeDescription[+T <: DisplayableData](@BeanProperty val parentNodeIndex: Int, @BeanProperty val childNodeIndex: Int)
  extends EdgeCreationDescription[T]
