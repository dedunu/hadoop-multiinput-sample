package org.dedunu.hadoop.muiltiinputsample;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by dedunu on 5/21/15.
 */
public class SampleMapper extends Mapper<Text, Text, Text, Text> {

    public void map(Text key, Text value, Context context)
            throws IOException, InterruptedException {
            context.write(key, value);
    }
}