package xyz.formatter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import xyz.formatter.Formatter;

/**
 * Json格式的Formatter 
 * @author liuyazhuang
 *
 */
public class JsonFormatter implements Formatter {

    private static final String QUOTE = "\"";
    private static final String COLON = ":";
    private static final String COMMA = ",";

    private boolean expectJson = false;

    public String format(ILoggingEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        fieldName("timestamp", sb);
        sb.append(event.getTimeStamp());
        sb.append(COMMA);
        fieldName("level", sb);
        quote(event.getLevel().levelStr, sb);
        sb.append(COMMA);
        fieldName("thread", sb);
        quote(event.getThreadName(), sb);
        sb.append(COMMA);
        fieldName("logger", sb);
        quote(event.getLoggerName(), sb);
        sb.append(COMMA);
        fieldName("message", sb);
        if(this.expectJson){
            sb.append(event.getFormattedMessage());
        }else{
            quote(event.getFormattedMessage(), sb);
        }
        sb.append("}");
        return sb.toString();
    }

    private void fieldName(String name, StringBuilder sb){
        quote(name, sb);
        sb.append(COLON);
    }

    private void quote(String value, StringBuilder sb){
        sb.append(QUOTE);
        sb.append(value);
        sb.append(QUOTE);
    }

    public boolean isExpectJson() {
        return expectJson;
    }

    public void setExpectJson(boolean expectJson) {
        this.expectJson = expectJson;
    }
}  