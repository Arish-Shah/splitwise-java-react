import { createContext, use, useState } from "react";
import toast from "react-hot-toast";
import { http } from "../util/http";

const TOKEN = "token";
const EXPIRATION = "tokenExpiration";

const AuthContext = createContext({
  token: null,
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

function saveToken(token) {
  localStorage.setItem(TOKEN, token);
  localStorage.setItem(
    EXPIRATION,
    new Date(Date.now() + 60 * 60 * 1000).toISOString()
  );
}

function getToken() {
  const token = localStorage.getItem(TOKEN);
  const expiration = localStorage.getItem(EXPIRATION);

  if (token && expiration && new Date(expiration) > new Date()) {
    return token;
  } else {
    localStorage.removeItem(TOKEN);
    localStorage.removeItem(EXPIRATION);
    return null;
  }
}

export function AuthProvider({ children }) {
  const [token, setToken] = useState(getToken());

  const signup = (data) =>
    toast.promise(() => http.post("/auth/signup", data), {
      loading: "Submitting...",
      success: ({ token }) => {
        setToken(token);
        saveToken(token);
      },
      error: (e) => e.message,
    });

  const signin = (data) =>
    toast.promise(() => http.post("/auth/signin", data), {
      loading: "Submitting...",
      success: ({ token }) => {
        setToken(token);
        saveToken(token);
      },
      error: (e) => e.message,
    });

  const signout = () => {
    setToken(null);
    localStorage.removeItem(TOKEN);
    localStorage.removeItem(EXPIRATION);
  };

  return (
    <AuthContext value={{ token, signup, signin, signout }}>
      {children}
    </AuthContext>
  );
}
