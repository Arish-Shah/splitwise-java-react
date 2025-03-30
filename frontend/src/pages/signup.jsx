import { Link } from "react-router";
import { FormInput, FormButton } from "../components/form";
import { useRef } from "react";
import { useAuthContext } from "../context/auth-context";

export default function Signup() {
  const authContext = useAuthContext();

  const nameRef = useRef();
  const emailRef = useRef();
  const passwordRef = useRef();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = {
      name: nameRef.current.value,
      email: emailRef.current.value,
      password: passwordRef.current.value,
    };

    authContext.signup(data);
  };

  return (
    <>
      <div className="mt-3 w-full rounded-lg bg-white p-4 pt-2 shadow-sm">
        <form onSubmit={handleSubmit}>
          <FormInput
            type="text"
            id="name"
            label="Full name"
            placeholder="Jane Doe"
            ref={nameRef}
            autoFocus
          />
          <FormInput
            type="email"
            id="email"
            placeholder="user@mail.com"
            label="Email address"
            ref={emailRef}
            required
          />
          <FormInput
            type="password"
            id="password"
            placeholder="******"
            label="Password"
            minLength={4}
            ref={passwordRef}
            required
          />
          <FormButton>Create account</FormButton>
        </form>
      </div>
      <div className="mt-2 text-center text-sm text-zinc-500">
        Have an account already?{" "}
        <Link to="/signin" className="underline">
          Sign in
        </Link>
      </div>
    </>
  );
}
