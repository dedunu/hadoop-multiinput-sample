package org.dedunu.hadoop.muiltiinputsample;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

import java.io.IOException;

/**
 * Created by dedunu on 5/21/15.
 */
public class MobileInputFormat extends FileInputFormat<Text, Text> {
    public RecordReader<Text, Text> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        return new AirlineInputFormatClass();
    }

    public class AirlineInputFormatClass extends RecordReader<Text, Text> {
        private LineRecordReader lineRecordReader = null;
        private Text key = null;
        private Text value = null;

        @Override
        public void close() throws IOException {
            if (null != lineRecordReader) {
                lineRecordReader.close();
                lineRecordReader = null;
            }
            key = null;
            value = null;
        }

        @Override
        public Text getCurrentKey() throws IOException, InterruptedException {
            return key;
        }

        @Override
        public Text getCurrentValue() throws IOException, InterruptedException {
            return value;
        }

        @Override
        public float getProgress() throws IOException, InterruptedException {
            return lineRecordReader.getProgress();
        }

        @Override
        public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
            close();

            lineRecordReader = new LineRecordReader();
            lineRecordReader.initialize(split, context);
        }

        @Override
        public boolean nextKeyValue() throws IOException, InterruptedException {
            if (!lineRecordReader.nextKeyValue()) {
                key = null;
                value = null;
                return false;
            }

            // This is where you should implement your custom logic.
            Text line = lineRecordReader.getCurrentValue();
            String str = line.toString();
            String[] arr = str.split("\\t");

            key = new Text(arr[0]);
            value = new Text(arr[3]);

            return true;
        }

    }
}