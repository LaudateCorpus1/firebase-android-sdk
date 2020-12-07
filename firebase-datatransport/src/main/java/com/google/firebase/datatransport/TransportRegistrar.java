// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.firebase.datatransport;

import android.content.Context;
import androidx.annotation.Keep;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.platforminfo.UserAgentPublisher;

import java.util.Collections;
import java.util.List;

@Keep
public class TransportRegistrar implements ComponentRegistrar {
  @Override
  public List<Component<?>> getComponents() {
    return Collections.singletonList(
        Component.builder(FirebaseDataTransport.class)
            .add(Dependency.required(Context.class))
            .add(Dependency.requiredProvider(HeartBeatInfo.class))
            .add(Dependency.requiredProvider(UserAgentPublisher.class))
            .factory(
                c -> new DefaultFirebaseDataTransport(c.get(Context.class), c.getProvider(HeartBeatInfo.class), c.getProvider(UserAgentPublisher.class)))
            .build());
  }
}
