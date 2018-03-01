package org.sample

import spock.lang.Specification

class CsvJsonHelperSpec extends Specification{


    private static final String const1 = '[{"id": 1463, "name": "culture clash"}, {"id": 2964, "name": "future"}, {"id": 3386, "name": "space war"}, {"id": 3388, "name": "space colony"}, {"id": 3679, "name": "society"}, {"id": 3801, "name": "space travel"}, {"id": 9685, "name": "futuristic"}, {"id": 9840, "name": "romance"}, {"id": 9882, "name": "space"}, {"id": 9951, "name": "alien"}, {"id": 10148, "name": "tribe"}, {"id": 10158, "name": "alien planet"}, {"id": 10987, "name": "cgi"}, {"id": 11399, "name": "marine"}, {"id": 13065, "name": "soldier"}, {"id": 14643, "name": "battle"}, {"id": 14720, "name": "love affair"}, {"id": 165431, "name": "anti war"}, {"id": 193554, "name": "power relations"}, {"id": 206690, "name": "mind and soul"}, {"id": 209714, "name": "3d"}]'

    def setup() {
    }

    def cleanup() {
    }

    void "test fixQuotes usual"() {
        expect:
            "" == CsvJsonHelper.fixQuoting("")
            const1 == CsvJsonHelper.fixQuoting(const1)
    }

    void "fix starting triple-double-quotes"() {
        expect:
            CsvJsonHelper.fixQuoting('{"name": """DIA"" Productions GmbH & Co. KG", "id": 37103}').length() == 58
            '{"name": "\\"DIA\\" Productions GmbH & Co. KG", "id": 37103}' == CsvJsonHelper.fixQuoting('{"name": """DIA"" Productions GmbH & Co. KG", "id": 37103}')
    }

    void "fix ending triple-double-quote"() {
        expect:
            '{"id": 231846, "name": "operation \\"trudy jackson\\""}' == CsvJsonHelper.fixQuoting('{"id": 231846, "name": "operation ""trudy jackson"""}')

    }

}
