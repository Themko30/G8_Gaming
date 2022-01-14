package main.java.Storage.Entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;

public class Utente {

    /**
     * L'username univoco dell'utente.
     */
    private String username;

    /**
     * L'email univoca dell'utente.
     */
    private String email;

    /**
     * La password hashata dell'utente.
     */
    private String password;

    /**
     * Il nome dell'utente.
     */
    private String nome;

    /**
     * Il cognome dell'utente.
     */
    private String cognome;

    /**
     * Il sesso dell'utente.
     */
    private String sesso;

    /**
     * L'indirizzo dell'utente.
     */
    private String indirizzo;

    /**
     * Il paese dell'utente.
     */
    private String paese;

    /**
     * Il CAP dell'utente.
     */
    private int cap;

    /**
     * La data di nascita dell'utente.
     */
    private LocalDate dataDiNascita;

    /**
     * Il flag admin dell'utente. Se questa variabile ha valore 1,
     * l'utente è un amministratore del sistema.
     */
    private boolean admin;

    /**
     * La lista degli ordini dell'utente.
     */
    private List<Ordine> ordini;

    /**
     * Metodo Getter per ottenere l'username dell'utente.
     * @return L'username dell'utente
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo Setter per impostare l'username dell'utente.
     * @param username L'username da impostare
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo Getter per ottenere l'email dell'utente.
     * @return L'email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Metodo Setter per impostare l'email dell'utente.
     * @param email L'email da impostare
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo Getter per ottenere la password dell'utente.
     * @return La password hashata dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo Setter per impostare la password hashata dell'utente.
     * @param password La password da hashare da impostare
     */
    public void setPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            SecureRandom ss = new SecureRandom();
            byte[] hashedPwd =
                    digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte bit : hashedPwd) {
                builder.append(String.format("%02x", bit));
            }
            this.password = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo Getter per ottenere il nome dell'utente.
     * @return Il nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo Setter per impostare il nome dell'utente.
     * @param nome Il nome da impostare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo Getter per ottenere il cognome dell'utente.
     * @return Il cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo Setter per impostare il cognome dell'utente.
     * @param cognome Il cognome da impostare
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Metodo Getter per ottenere il sesso dell'utente.
     * @return Il sesso dell'utente
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * Metodo Setter per impostare il sesso dell'utente.
     * @param sesso Il sesso da impostare
     */
    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    /**
     * Metodo Getter per ottenere la data di nascita dell'utente.
     * @return La data di nascita dell'utente
     */
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Metodo Setter per impostare la data di nascita dell'utente.
     * @param dataDiNascita La data da impostare
     */
    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * Metodo Getter per ottenere il flag admin dell'utente.
     * @return 1 se l'utente è amministratore, 0 altrimenti
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Metodo Setter per impostare il flag admin dell'utente.
     * @param admin 1 se l'utente è un ammisistratore, 0 altrimenti
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Metodo Getter per ottenere gli ordini dell'utente.
     * @return La lista degli ordini dell'utente
     */
    public List<Ordine> getOrdini() {
        return ordini;
    }

    /**
     * Metodo Setter per impostare la lista dell'utente.
     * @param ordini La lista da impostare
     */
    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    /**
     * Metodo Getter per ottenere l'indirizzo dell'utente.
     * @return L'indirizzo dell'utente
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Metodo Setter per impostare l'indirizzo dell'utente.
     * @param indirizzo L'indirizzo da impostare
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo Getter per ottenere il paese dell'utente.
     * @return Il paese dell'utente
     */
    public String getPaese() {
        return paese;
    }

    /**
     * Metodo Setter per impostare il paese dell'utente.
     * @param paese Il paese da impostare
     */
    public void setPaese(String paese) {
        this.paese = paese;
    }

    /**
     * Metodo Getter per ottenere il CAP dell'utente.
     * @return Il CAP dell'utente
     */
    public int getCap() {
        return cap;
    }

    /**
     * Metodo Setter per impostare il CAP dell'utente.
     * @param cap Il CAP da impostare
     */
    public void setCap(int cap) {
        this.cap = cap;
    }
}
