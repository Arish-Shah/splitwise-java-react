import { useAuthContext } from "../context/auth-context";

export default function Home() {
  const authContext = useAuthContext();

  return (
    <>
      <h2 className="text-lg font-medium">
        Hi, {authContext.name}. Send a new payment request.
      </h2>
    </>
  );
}
