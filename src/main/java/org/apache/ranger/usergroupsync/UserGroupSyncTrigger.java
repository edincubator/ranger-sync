package org.apache.ranger.usergroupsync;

import org.apache.log4j.Logger;
import org.apache.ranger.unixusersync.config.UserGroupSyncConfig;

public class UserGroupSyncTrigger {

    private static final Logger LOG = Logger.getLogger(UserGroupSync.class);

    public static void main(String[] args) {
        UserGroupSink ugSink;
        UserGroupSource ugSource;

        try {
            ugSink = UserGroupSyncConfig.getInstance().getUserGroupSink();
            LOG.info("initializing sink: " + ugSink.getClass().getName());
            ugSink.init();

            ugSource = UserGroupSyncConfig.getInstance().getUserGroupSource();
            LOG.info("initializing source: " + ugSource.getClass().getName());
            ugSource.init();

            LOG.info("Begin: initial load of user/group from source==>sink");
            ugSource.updateSink(ugSink);
            LOG.info("End: initial load of user/group from source==>sink");

            LOG.info("Done initializing user/group source and sink");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

    }
}
