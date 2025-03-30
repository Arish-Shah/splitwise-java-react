import { Link } from "react-router";
import { FormButton, FormInput } from "../components/form";
import { useAuthContext } from "../context/auth-context";
import { useRef } from "react";

export default function Singin() {
  const authContext = useAuthContext();

  const emailRef = useRef();
  const passwordRef = useRef();

  const handleSubmit = (e) => {
    e.preventDefault();

    const data = {
      email: emailRef.current.value,
      password: passwordRef.current.value,
    };

    authContext.signin(data);
  };

  return (
    <>
      <div className="mt-3 w-full rounded-lg bg-white p-4 pt-2 shadow-sm">
        <form onSubmit={handleSubmit}>
          <FormInput
            type="email"
            id="email"
            placeholder="user@mail.com"
            label="Email address"
            ref={emailRef}
            required
            autoFocus
          />
          <FormInput
            type="password"
            id="password"
            placeholder="******"
            label="Password"
            ref={passwordRef}
            required
          />
          <FormButton>Log in to your account</FormButton>
        </form>
      </div>
      <div className="mt-2 text-center text-sm text-zinc-500">
        Want to create an account?{" "}
        <Link to="/signup" className="underline">
          Sign up
        </Link>
      </div>
    </>
  );
}
