/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.spinnaker.orca.pipelinetemplate.v1schema.graph.transform;

import com.netflix.spinnaker.orca.pipelinetemplate.v1schema.PipelineTemplateVisitor;
import com.netflix.spinnaker.orca.pipelinetemplate.v1schema.model.PipelineTemplate;
import com.netflix.spinnaker.orca.pipelinetemplate.v1schema.model.TemplateConfiguration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PipelineConfigInheritanceTransform implements PipelineTemplateVisitor {

  private TemplateConfiguration templateConfiguration;

  public PipelineConfigInheritanceTransform(TemplateConfiguration templateConfiguration) {
    this.templateConfiguration = templateConfiguration;
  }

  @Override
  public void visitPipelineTemplate(PipelineTemplate pipelineTemplate) {
    List<String> inherit = templateConfiguration.getConfiguration().getInherit();
    PipelineTemplate.Configuration pc = pipelineTemplate.getConfiguration();

    if (!inherit.contains("concurrentExecutions")) {
      pc.setConcurrentExecutions(new HashMap<>());
    }
    if (!inherit.contains("triggers")) {
      pc.setTriggers(Collections.emptyList());
    }
    if (!inherit.contains("parameters")) {
      pc.setParameters(Collections.emptyList());
    }
    if (!inherit.contains("expectedArtifacts")) {
      pc.setExpectedArtifacts(Collections.emptyList());
    }
    if (!inherit.contains("notifications")) {
      pc.setNotifications(Collections.emptyList());
    }
  }
}
