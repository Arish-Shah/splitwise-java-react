import { Outlet, useNavigate } from "react-router";
import { useAuthContext } from "../context/auth-context";
import { useEffect } from "react";
import { Nav } from "../components/nav";

export function UserLayout() {
  const authContext = useAuthContext();
  const navigate = useNavigate();

  useEffect(() => {
    if (!authContext.token) {
      navigate("/signin", { replace: true });
    }
  }, [authContext.token]);

  return (
    <div className="flex w-full items-center justify-center">
      <div className="w-full max-w-3xl">
        <Nav />
        <Outlet />
      </div>
    </div>
  );
}
