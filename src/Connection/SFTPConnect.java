package Connection;

import com.jcraft.jsch.*;
public class SFTPConnect
{
    public static void connectSFTP(String host, int port, String username, String password, String remoteFilePath, String localFilePath)
    {
        JSch jsch = new JSch();
        try
        {
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            channelSftp.get(remoteFilePath, localFilePath);

            channelSftp.disconnect();
            session.disconnect();
        }
        catch (JSchException | SftpException e)
        {
            e.printStackTrace();
        }
    }
}