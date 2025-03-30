import { createContext, use, useState } from "react";
import toast from "react-hot-toast";
import { http } from "../util/http";

const TOKEN = "token";
const NAME = "name";
const EMAIL = "email";
const EXPIRATION = "tokenExpiration";

const AuthContext = createContext({
  token: null,
  name: null,
  email: null,
  signup: null,
  signin: null,
  signout: null,
});

export function useAuthContext() {
  const authContext = use(AuthContext);

  if (!authContext) {
    throw new Error("useAuthContext must be used inside an AuthProvider");
  }

  return authContext;
}

function saveToken(data) {
  localStorage.setItem(TOKEN, data.token);
  localStorage.setItem(NAME, data.name);
  localStorage.setItem(EMAIL, data.email);
  localStorage.setItem(
    EXPIRATION,
    new Date(Date.now() + 60 * 60 * 1000).toISOString()
  );
}

function getSaved() {
  const token = localStorage.getItem(TOKEN);
  const name = localStorage.getItem(NAME);
  const email = localStorage.getItem(EMAIL);
  const expiration = localStorage.getItem(EXPIRATION);

  if (!expiration || new Date(expiration) < new Date()) {
    localStorage.removeItem(TOKEN);
    localStorage.removeItem(EXPIRATION);
  }
  return { token, name, email };
}

export function AuthProvider({ children }) {
  const [token, setToken] = useState(getSaved().token);
  const [name, setName] = useState(getSaved().name);
  const [email, setEmail] = useState(getSaved().email);

  const signup = (data) =>
    toast.promise(() => http.post("/auth/signup", data), {
      loading: "Submitting...",
      success: (data) => {
        setToken(data.token);
        setName(data.name);
        setEmail(data.email);
        saveToken(data);
      },
      error: (e) => e.message,
    });

  const signin = (data) =>
    toast.promise(() => http.post("/auth/signin", data), {
      loading: "Submitting...",
      success: (data) => {
        setToken(data.token);
        setName(data.name);
        setEmail(data.email);
        saveToken(data);
      },
      error: (e) => e.message,
    });

  const signout = () => {
    setToken(null);
    localStorage.removeItem(TOKEN);
    localStorage.removeItem(NAME);
    localStorage.removeItem(EMAIL);
    localStorage.removeItem(EXPIRATION);
  };

  return (
    <AuthContext value={{ token, name, email, signup, signin, signout }}>
      {children}
    </AuthContext>
  );
}
