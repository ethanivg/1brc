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
import com.invirgance.convirgance.source.ClasspathSource;
import com.invirgance.convirgance.transform.CoerceStringsTransformer;
import java.io.IOException;

public class CalculateAverage_ethanivg {

    private static final String FILE = "./measurements.txt";

    

    public static void main(String[] args) throws IOException {
        
        ClasspathSource source = new ClasspathSource(FILE);
        DelimitedInput input = new DelimitedInput();
        CoerceStringsTransformer transformer = new CoerceStringsTransformer();
        JSONArray weatherStationInfo = new JSONArray();
        int c = 0;
        for (JSONObject record : input.read(source))
        {
            if(c >= 3){
                break;
            }
            weatherStationInfo.add(record);
        }
        
        System.out.println(weatherStationInfo);
    }
}

//        Collector<Measurement, MeasurementAggregator, ResultRow> collector = Collector.of(
//                MeasurementAggregator::new,
//                (a, m) -> {
//                    a.min = Math.min(a.min, m.value);
//                    a.max = Math.max(a.max, m.value);
//                    a.sum += m.value;
//                    a.count++;
//                },
//                (agg1, agg2) -> {
//                    var res = new MeasurementAggregator();
//                    res.min = Math.min(agg1.min, agg2.min);
//                    res.max = Math.max(agg1.max, agg2.max);
//                    res.sum = agg1.sum + agg2.sum;
//                    res.count = agg1.count + agg2.count;
//
//                    return res;
//                },
//                agg -> {
//                    return new ResultRow(agg.min, (Math.round(agg.sum * 10.0) / 10.0) / agg.count, agg.max);
//                });
//
//        Map<String, ResultRow> measurements = new TreeMap<>(Files.lines(Paths.get(FILE))
//                .map(l -> new Measurement(l.split(";")))
//                .collect(groupingBy(m -> m.station(), collector)));
//private static record Measurement(String station, double value) {
//        private Measurement(String[] parts) {
//            this(parts[0], Double.parseDouble(parts[1]));
//        }
//    }
//
//    private static record ResultRow(double min, double mean, double max) {
//
//        public String toString() {
//            return round(min) + "/" + round(mean) + "/" + round(max);
//        }
//
//        private double round(double value) {
//            return Math.round(value * 10.0) / 10.0;
//        }
//    };
//
//    private static class MeasurementAggregator {
//        private double min = Double.POSITIVE_INFINITY;
//        private double max = Double.NEGATIVE_INFINITY;
//        private double sum;
//        private long count;
//    }