/*
 *  Copyright 2023 The original authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package dev.morling.onebrc;

import com.invirgance.convirgance.input.DelimitedInput;
import com.invirgance.convirgance.json.JSONArray;
import com.invirgance.convirgance.json.JSONObject;
import com.invirgance.convirgance.source.FileSource;
import com.invirgance.convirgance.transform.CoerceStringsTransformer;
import java.io.File;
import java.io.IOException;

public class CalculateAverage_ethanivg {

    private static final String FILE = "D:\\1brc\\measurements.txt";

    public static void main(String[] args) throws IOException {
        FileSource source = new FileSource(new File(FILE));
        
        DelimitedInput input = new DelimitedInput(new String[]{
                "city", "temp"
        }, ';');
        
        CoerceStringsTransformer transformer = new CoerceStringsTransformer();
        
        JSONArray weatherStationInfo = new JSONArray();
        int c = 0;
        
        // TODO: Transformer to group data by keys
        // TODO: Transformer min/mean/max
        //         - Filter on grouped data for min/max
        //         - Example out Abha=-23.0/18.0/59.2
        
        for (JSONObject record : transformer.transform(input.read(source))) {
            if (c >= 3) break;
            c++;
            weatherStationInfo.add(record);
        }
        
        System.out.println(weatherStationInfo);
    }
}
