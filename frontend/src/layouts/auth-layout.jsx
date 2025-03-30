import { Outlet, useNavigate } from "react-router";
import { Brand } from "../components/brand";
import { useAuthContext } from "../context/auth-context";
import { useEffect } from "react";

export function AuthLayout() {
  const authContext = useAuthContext();
  const navigate = useNavigate();

  useEffect(() => {
    if (authContext.token) {
      navigate("/", { replace: true });
    }
  }, [authContext.token]);

  return (
    <div className="flex h-screen w-full items-center justify-center">
      <div className="w-full max-w-sm p-3">
        <Brand size="lg" />
        <Outlet />
      </div>
    </div>
  );
}
