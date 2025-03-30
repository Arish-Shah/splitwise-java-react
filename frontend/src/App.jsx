import { Routes, Route } from "react-router";
import Home from "./pages/home";
import { AuthLayout } from "./layouts/auth-layout";
import Signin from "./pages/signin";
import Signup from "./pages/signup";
import { AuthProvider } from "./context/auth-context";
import { UserLayout } from "./layouts/user-layout";
import Transactions from "./pages/transactions";

export default function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route element={<UserLayout />}>
          <Route index element={<Home />} />
          <Route path="/transactions" element={<Transactions />} />
        </Route>
        <Route element={<AuthLayout />}>
          <Route path="/signin" element={<Signin />} />
          <Route path="/signup" element={<Signup />} />
        </Route>
      </Routes>
    </AuthProvider>
  );
}
