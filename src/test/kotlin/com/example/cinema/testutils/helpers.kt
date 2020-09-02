package com.example.cinema.testutils

import org.springframework.core.io.DefaultResourceLoader
import org.springframework.util.FileCopyUtils
import java.io.InputStreamReader

fun getResourceContent(location: String): String {
    val resource = DefaultResourceLoader().getResource(location)
    return FileCopyUtils.copyToString(InputStreamReader(resource.inputStream))
}