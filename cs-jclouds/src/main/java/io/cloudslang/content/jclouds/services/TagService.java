package io.cloudslang.content.jclouds.services;

import io.cloudslang.content.jclouds.entities.inputs.CommonInputs;
import io.cloudslang.content.jclouds.entities.inputs.CustomInputs;

/**
 * Created by Mihai Tusa.
 * 7/20/2016.
 */
public interface TagService {
    void applyToResources(CommonInputs commonInputs, CustomInputs customInputs);
}