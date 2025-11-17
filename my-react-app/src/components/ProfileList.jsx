import ProfileCard from "./ProfileCard";

export default function ProfileList() {
  return (
    <div>
      <h2>Profile List</h2>
      <ProfileCard name="Alice" age={25} role="Developer" />
      <ProfileCard name="Bob" age={30} role="Designer" />
      <ProfileCard name="Charlie" age={28} role="Manager" />
    </div>
  );
}
