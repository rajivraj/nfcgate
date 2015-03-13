package tud.seemuh.nfcgate.network;

import tud.seemuh.nfcgate.hce.ApduService;

public interface Callback {
    public void onDataReceived(byte[] data);

    public Callback setAPDUService(ApduService as);

    public void notifyBrokenPipe();

    public void shutdown();

    public void disconnectCardWorkaround();
}
