package Connection;

import com.jcraft.jsch.*;
public class SFTPConnect
{
    public static void connectSFTP(String host, int port, String username, String password, String[] remoteFilePaths, String[] localFilePaths) {
        JSch jsch = new JSch();
        try {
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            for (int i = 0; i < remoteFilePaths.length; i++) {
                String remoteFilePath = remoteFilePaths[i];
                String localFilePath = localFilePaths[i];
                channelSftp.get(remoteFilePath, localFilePath);
            }

            // TODO: decrypt and send the files back

            channelSftp.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            System.err.println("Error connecting to SFTP server: " + e.getMessage());
        }
    }
}