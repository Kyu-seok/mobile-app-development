package edu.curtin.comp2008.httpclient;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

public class DownloadUtils
{
    /**
     * Sets up HttpsURLConnection to accept a 'self-signed certificate'. Note: this code would be
     * *completely unnecessary* (and in fact bad) in a real-world situation. However, we need it
     * when testing and experimenting, because in this situation it's impractical to go and get a
     * proper certificate.
     *
     * @param context The Android context/activity. Needed in order to extract the certificate
     *                text itself from the app's resources.
     * @param conn The HTTPS connection that will use the certificate.
     * @throws IOException If we couldn't load the certificate or the KeyStore.
     * @throws GeneralSecurityException If any of the other various setup steps fail. We're not
     * performing the actual verification here though. If the certificate turns out not to match
     * the server's certificate, that will *only later on* trigger an exception.
     */
    public static void addCertificate(Context context, HttpsURLConnection conn)
        throws IOException, GeneralSecurityException
    {
        /**
         * Adapted in part from https://developer.android.com/training/articles/security-ssl#java
         */
        Certificate cert;
        try(InputStream is = context.getResources().openRawResource(R.raw.cert))
        {
            cert = CertificateFactory.getInstance("X.509").generateCertificate(is);
        }

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", cert);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
        conn.setSSLSocketFactory(sslContext.getSocketFactory());
        conn.setHostnameVerifier(new HostnameVerifier()
        {
            @Override
            public boolean verify(String hostname, SSLSession session)
            {
                return true;
            }
        });
    }
}