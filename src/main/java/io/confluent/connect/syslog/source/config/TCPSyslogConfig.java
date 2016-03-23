package io.confluent.connect.syslog.source.config;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.graylog2.syslog4j.SyslogConstants;
import org.graylog2.syslog4j.server.impl.net.tcp.TCPNetSyslogServer;
import org.graylog2.syslog4j.server.impl.net.tcp.TCPNetSyslogServerConfigIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class TCPSyslogConfig extends BaseSyslogConfig implements TCPNetSyslogServerConfigIF {
  private static final Logger log = LoggerFactory.getLogger(TCPSyslogConfig.class);

  public static final String TIMEOUT_CONFIG = "syslog.timeout";
  private static final String TIMEOUT_DOC = "Number of milliseconds before a timing out the connection.";

  public static final String BACKLOG_CONFIG = "syslog.backlog";
  private static final String BACKLOG_DOC = "Number of connections to allow in backlog.";

  public static final String MAX_ACTIVE_SOCKETS_CONFIG = "syslog.max.active.sockets";
  private static final String MAX_ACTIVE_SOCKETS_DOC = "Maximum active sockets";

  public static final String MAX_ACTIVE_SOCKETS_BEHAVIOR_CONFIG = "syslog.max.active.sockets.behavior";
  private static final String MAX_ACTIVE_SOCKETS_BEHAVIOR_DOC = "Maximum active sockets";

  protected static ConfigDef getConfig() {
    return baseConfig()
        .define(TIMEOUT_CONFIG, Type.INT, 0, Importance.LOW, TIMEOUT_DOC)
        .define(BACKLOG_CONFIG, Type.INT, SyslogConstants.SERVER_SOCKET_BACKLOG_DEFAULT, ConfigDef.Range.atLeast(1), Importance.LOW, BACKLOG_DOC)
        .define(MAX_ACTIVE_SOCKETS_CONFIG, Type.INT, TCPNetSyslogServerConfigIF.TCP_MAX_ACTIVE_SOCKETS_DEFAULT, Importance.LOW, MAX_ACTIVE_SOCKETS_DOC)
        .define(MAX_ACTIVE_SOCKETS_BEHAVIOR_CONFIG, Type.INT, (int)TCPNetSyslogServerConfigIF.MAX_ACTIVE_SOCKETS_BEHAVIOR_BLOCK, Importance.LOW, MAX_ACTIVE_SOCKETS_BEHAVIOR_DOC)
        ;
  }

  public TCPSyslogConfig(ConfigDef definition, Map<String, String> originals) {
    super(definition, originals);
  }

  public TCPSyslogConfig(Map<String, String> originals) {
    super(getConfig(), originals);
  }

  @Override
  public int getTimeout() {
    return this.getInt(TIMEOUT_CONFIG);
  }

  @Override
  public void setTimeout(int i) {
    log.warn("Setting {} to {}. Should not happen", TIMEOUT_CONFIG, i);

    throw new UnsupportedOperationException();
  }

  @Override
  public int getBacklog() {
    return this.getInt(BACKLOG_CONFIG);
  }

  @Override
  public void setBacklog(int i) {
    log.warn("Setting {} to {}. Should not happen", BACKLOG_CONFIG, i);
    throw new UnsupportedOperationException();
  }

  @Override
  public int getMaxActiveSockets() {
    return this.getInt(MAX_ACTIVE_SOCKETS_CONFIG);
  }

  @Override
  public void setMaxActiveSockets(int i) {
    log.warn("Setting {} to {}. Should not happen", MAX_ACTIVE_SOCKETS_CONFIG, i);
    throw new UnsupportedOperationException();
  }

  @Override
  public byte getMaxActiveSocketsBehavior() {
    return (byte) this.get(MAX_ACTIVE_SOCKETS_BEHAVIOR_CONFIG);
  }

  @Override
  public void setMaxActiveSocketsBehavior(byte b) {
    log.warn("Setting {} to {}. Should not happen", MAX_ACTIVE_SOCKETS_BEHAVIOR_CONFIG, b);
    throw new UnsupportedOperationException();
  }

  @Override
  public Class getSyslogServerClass() {
    return TCPNetSyslogServer.class;
  }
}