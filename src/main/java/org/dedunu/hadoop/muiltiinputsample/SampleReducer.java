package org.dedunu.hadoop.muiltiinputsample;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by dedunu on 5/21/15.
 */
public class SampleReducer extends Reducer<Text, Text, Text, Text> {
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        int totalAmount = 0;
        for (Text value : values) {
            String valueString = value.toString();
            int currentAmount = 0;

            try {
                currentAmount = Integer.parseInt(valueString);
            } catch (NumberFormatException exception) {
                // If you have your own loggin system log it here.
                // logger.error("Blah blah blah");
            }

            totalAmount += currentAmount;
        }

        context.write(key, new Text(Integer.toString(totalAmount)));

    }
}