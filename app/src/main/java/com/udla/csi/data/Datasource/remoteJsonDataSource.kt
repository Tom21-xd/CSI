package com.udla.csi.data.Datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class remoteJsonDataSource {

    suspend fun fetchJson(): String {
        return withContext(Dispatchers.IO) {
            URL("https://raw.githubusercontent.com/marcovega/colombia-json/master/colombia.min.json").readText()
        }
    }

}