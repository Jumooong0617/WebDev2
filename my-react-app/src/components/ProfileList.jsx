import ProfileCard from "./ProfileCard";

export default function ProfileList() {
  return (
    <div>
      <h2>Profile List</h2>
      <ProfileCard name="Ulysses" age={25} role="Jungler" />
      <ProfileCard name="Zedric" age={30} role="Roamer" />
      <ProfileCard name="Bench" age={28} role="Gold" />
    </div>
  );
}
