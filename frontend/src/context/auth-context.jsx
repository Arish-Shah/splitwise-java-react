import { createContext, use, useState } from "react";
import toast from "react-hot-toast";
import { http } from "../util/http";

const TOKEN = "token";
const NAME = "name";
const EXPIRATION = "tokenExpiration";

const AuthContext = createContext({
  token: null,
  name: null,
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
  localStorage.setItem(
    EXPIRATION,
    new Date(Date.now() + 60 * 60 * 1000).toISOString()
  );
}

function getSaved() {
  const token = localStorage.getItem(TOKEN);
  const name = localStorage.getItem(NAME);
  const expiration = localStorage.getItem(EXPIRATION);

  if (token && expiration && new Date(expiration) > new Date()) {
    return [token, name];
  } else {
    localStorage.removeItem(TOKEN);
    localStorage.removeItem(EXPIRATION);
    return [null, null];
  }
}

export function AuthProvider({ children }) {
  const [savedToken, savedName] = getSaved();

  const [token, setToken] = useState(savedToken);
  const [name, setName] = useState(savedName);

  const signup = (data) =>
    toast.promise(() => http.post("/auth/signup", data), {
      loading: "Submitting...",
      success: (data) => {
        setToken(data.token);
        setName(data.name);
        saveToken(data.token);
      },
      error: (e) => e.message,
    });

  const signin = (data) =>
    toast.promise(() => http.post("/auth/signin", data), {
      loading: "Submitting...",
      success: (data) => {
        setToken(data.token);
        setName(data.name);
        saveToken(data.token);
      },
      error: (e) => e.message,
    });

  const signout = () => {
    setToken(null);
    localStorage.removeItem(TOKEN);
    localStorage.removeItem(NAME);
    localStorage.removeItem(EXPIRATION);
  };

  return (
    <AuthContext value={{ token, name, signup, signin, signout }}>
      {children}
    </AuthContext>
  );
}
