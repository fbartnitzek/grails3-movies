package org.sample

class CsvJsonHelper {

    public static String fixQuoting(String csvText){

        if (csvText.contains('"""')){
//            println("before fixQuoting: ${csvText}")
            def result = csvText.replaceAll(~/"""([^"]+)""/, '"\\\\"$1\\\\"')
            result = result.replaceAll(~/""([^"]+)"""/, '\\\\"$1\\\\""')
//            println("after fixQuoting: ${result}")
            result

        } else {
            csvText
        }
    }
}
